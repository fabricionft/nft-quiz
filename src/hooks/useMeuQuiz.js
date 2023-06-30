import { useNavigate } from "react-router-dom";
import useMessageBox from "./useMessageBox";
import useSession from "./useSession";
import api from "../services/api";

const useMeuQuiz = (id) =>{

  const {exibir} = useMessageBox();
  const{codigo} = useSession();
  const navigate = useNavigate();

  const salvarQuiz = (quiz) =>{
    api.post("/quizzes/"+codigo, {
      nome: quiz.nome,
      tema: quiz.tema,
      descricao: quiz.descricao
    })
    .then(() => {
      navigate("/meusQuizzes/"+codigo,
      {state:{
        msg: "Quiz salvo com sucesso!",
        type: "sucess",
        txtBotao: "Prosseguir"
      }});
      exibir();
    })
    .catch((error) =>{
      navigate(
      {state:{
        msg: error.response.data.message,
        type: "error",
        txtBotao: "Tentar novamente"
      }});
      exibir();
    })
  }

  const editarQuiz = (quiz) =>{
    api.put("/quizzes", {
      codigo: quiz.codigo,
      nome: quiz.nome,
      tema: quiz.tema,
      descricao: quiz.descricao
    })
    .then(() => {
      navigate("/meusQuizzes/"+codigo,
      {state:{
        msg: "Quiz editado com sucesso!",
        type: "sucess",
        txtBotao: "Prosseguir"
      }});
      exibir();
    })
    .catch((error) =>{
      navigate(
      {state:{
        msg: error.response.data.message,
        type: "error",
        txtBotao: "Tentar novamente"
      }});
      exibir();
    })
  }

  const excluirQuiz = (id) =>{
    api.delete("/quizzes/"+id)
    .then(() => {
      navigate("/meusQuizzes/"+codigo,
      {state:{
        msg: "Quiz deletado com sucesso!",
        type: "sucess",
        txtBotao: "Prosseguir"
      }});
      exibir();
    })
    .catch((error) =>{
      navigate(
      {state:{
        msg: error.response.data.message,
        type: "error",
        txtBotao: "Tentar novamente"
      }});
      exibir();
    })
  }

  return {salvarQuiz, editarQuiz, excluirQuiz};
}

export default useMeuQuiz;