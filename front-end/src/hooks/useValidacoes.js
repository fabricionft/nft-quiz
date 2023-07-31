import { useLocation, useNavigate } from "react-router-dom";
import useMessageBox from "./useMessageBox";

const useValidacoes = () => {

  const {exibirMensagem} = useMessageBox();
  const location = useLocation();

  const validarUsuario = (usuario) => {
    let erros = (usuario.usuario && usuario.senha) ? 1 : -1;
    if(erros == 1) erros = (usuario.senha.length < 6 && location.pathname == "/cadastro") ? -2 : 1;
    
    if(erros == 1) return true;
    else {
      exibirMensagem(
        '',
        (erros == -1) ? "Preencha todos os campos" : "A senha precisa conter ao menos 6 digitos",
        1
      );
    }
  }  

  const validarQuiz = (quiz) => {
    let erros = (quiz.nome && quiz.tema && quiz.descricao) ? 1 : -1;
    
    if(erros == 1) return true;
    else {
      exibirMensagem(
        '',
        "Preencha todos os campos",
        1
      );
    }
  }  

  const validarPergunta = (pergunta) => {
    let erros = 0;
    for(var i = 0; i <= 3; i++){
      erros += (pergunta.questao && pergunta.alternativas[i] && pergunta.alternativaCorreta) ? 1 : -1;
    }

    if(erros == 4) return true;
    else {
      exibirMensagem(
        '',
        "Preencha todos os campos",
        1
      );
    }
  }  

  return{validarUsuario, validarQuiz, validarPergunta};
}

export default useValidacoes;