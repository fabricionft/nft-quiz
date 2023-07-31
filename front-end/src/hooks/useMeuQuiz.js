import useMessageBox from "./useMessageBox";
import useSession from "./useSession";
import api from "../services/api";
import useValidacoes from "./useValidacoes";

const useMeuQuiz = () =>{

  const {exibirMensagem} = useMessageBox();
  const{codigo} = useSession();
  const {validarQuiz} = useValidacoes();

  const salvarQuiz = (quiz) =>{
    if(validarQuiz(quiz)){
      api.post("/quizzes/"+codigo, {
        nome: quiz.nome,
        tema: quiz.tema,
        descricao: quiz.descricao
      })
      .then(() => {
        exibirMensagem(
          "/meusQuizzes/"+codigo,
          "Quiz salvo com sucesso!",
          0
        );
      })
      .catch((error) =>{
        exibirMensagem(
          '',
          error.response.data.message,
          1
        );
      })
    }
  }

  const editarQuiz = (quiz) =>{
    if(validarQuiz(quiz)){
      api.put("/quizzes", {
        codigo: quiz.codigo,
        nome: quiz.nome,
        tema: quiz.tema,
        descricao: quiz.descricao
      })
      .then(() => {
        exibirMensagem(
          "/meusQuizzes/"+codigo,
          "Quiz editado com sucesso!",
          0
        );
      })
      .catch((error) =>{
        exibirMensagem(
          '',
          error.response.data.message,
          1
        );
      })
    }
  }

  const excluirQuiz = (id) =>{
    api.delete("/quizzes/"+id)
    .then(() => {
      exibirMensagem(
        '',
        "Quiz deletado com sucesso!",
        0
      );
    })
    .catch((error) =>{
      exibirMensagem(
        '',
        error.response.data.message,
        1
      );
    })
  }

  return {salvarQuiz, editarQuiz, excluirQuiz};
}

export default useMeuQuiz;