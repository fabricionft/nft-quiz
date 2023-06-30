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
  const {exibir} = useMessageBox();
  const navigate = useNavigate();

  useEffect(() =>{
    api.get("/quizzes/"+id)
    .then((resp) => setPerguntas(resp.data.perguntas))
    .catch((error) => console.log(error));
  }, [id])

  const iniciarJogo = () =>{
    setIniciar(true);
    resetarJogo();
  }

  const resetarJogo = () =>{
    setIndice(0);
    localStorage.removeItem('respostas');
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
      navigate("/login",
      {state:{
        msg: error.response.data.message,
        type: "error",
        txtBotao: "Tentar novamente"
      }});
      exibir();
    });
  }

  return{iniciar, perguntas, indice,
         iniciarJogo, resetarJogo, responderQuestao};
}

export default useQuiz;