import { useEffect, useState } from "react";
import api from "../services/api";
import { useNavigate } from "react-router-dom";
import useMessageBox from "./useMessageBox";
import useLoader from "./useLoader";

const useMinhasPerguntas = (id) => {

  const navigate = useNavigate();
  const [perguntas, setPerguntas] = useState([]);
  const {exibir} = useMessageBox();
  const {verificar} = useLoader();

  if(id){
    useEffect(() => {
      api.get("/quizzes/"+id)
      .then((resp) => {
        verificar(resp.data.perguntas);
        setTimeout(() => {
          setPerguntas(resp.data.perguntas)
        }, 250);
      })
      .catch((error) => console.log(error));
    }, [perguntas])
  }

  const salvarPergunta = (id, pergunta) => {
    api.post("/perguntas/"+id, {
      questao: pergunta.questao,
      alternativas: pergunta.alternativas,
      alternativaCorreta: pergunta.alternativaCorreta
    })
    .then(() => {
      navigate("/meuQuiz/"+id,
      {state:{
        msg: "Pergunta adcionada com sucesso",
        type: "sucess",
        txtBotao: "Prosseguir"
      }});
      exibir();
    })
    .catch((error) => {
      navigate("/criarPergunta/"+id,
      {state:{
        msg: error.response.data.message,
        type: "error",
        txtBotao: "Tentar novamente"
      }});
      exibir();
    })
  }

  const excluirPergunta = (codigoQuiz, codigoPergunta) => {
    api.delete("/perguntas/"+codigoQuiz+"/"+codigoPergunta)
    .then(() => {
      navigate("/meuQuiz/"+id,
      {state:{
        msg: "Pergunta deletada com sucesso",
        type: "sucess",
        txtBotao: "Prosseguir"
      }});
      exibir();
    })
    .catch((error) => {
      navigate("/criarPergunta/"+codigoQuiz,
      {state:{
        msg: error.response.data.message,
        type: "error",
        txtBotao: "Tentar novamente"
      }});
      exibir();
    })
  }

  return {perguntas, salvarPergunta,  excluirPergunta};
}

export default useMinhasPerguntas;