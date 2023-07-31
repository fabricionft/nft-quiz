import { useNavigate, useParams } from "react-router-dom"
import api from "../services/api";
import { useEffect, useState } from "react";
import useMessageBox from "../hooks/useMessageBox";
import useSession from './useSession';

const useQuiz = () => {
  
  const {id} = useParams();
  const [iniciar, setIniciar] = useState(false);
  const [perguntas, setPerguntas] = useState([]);
  const [indice, setIndice] = useState(0);
  const {codigo} = useSession();
  const {exibirMensagem} = useMessageBox();
  const navigate = useNavigate();

  useEffect(() =>{
    api.get("/perguntas/quiz/"+id)
    .then((resp) => setPerguntas(resp.data))
    .catch((error) => {
      exibirMensagem(
        '',
        error.response.data.message,
        1
      );
    });
  }, [id])

  const iniciarJogo = () =>{
    setIniciar(true);
    setIndice(0);
    localStorage.removeItem('respostas');
  }

  const resetarJogo = () =>{
    setIniciar(false);
    setIndice(0);
  }

  const responderQuestao = (index) =>{
    const resp =[localStorage.getItem('respostas')];
    resp.push(index+1)
    localStorage.setItem('respostas', resp)
    setIndice(indice+1)

    if(!perguntas[indice+1]) enviarRespostas();
  }

  const enviarRespostas = () =>{
    let respostas = [];
    localStorage.getItem('respostas').split(',').forEach(resposta => {
      if(resposta) respostas.push(parseInt(resposta));
    })
    
    api.post("/historicos/gerarResultados/"+codigo+"/"+id, respostas)
    .then((resp) =>{
      navigate("/resultados", 
      {state: {
        resultado: resp.data
      }});
    })
    .catch((error) =>{
      exibirMensagem(
        '',
        error.response.data.message,
        1
      );
    });
  }

  return{iniciar, perguntas, indice,
         iniciarJogo, resetarJogo, responderQuestao};
}

export default useQuiz;