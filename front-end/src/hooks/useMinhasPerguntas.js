import { useEffect, useState } from "react";
import api from "../services/api";
import useMessageBox from "./useMessageBox";
import useLoader from "./useLoader";
import useValidacoes from "./useValidacoes";

const useMinhasPerguntas = (id) => {

  const [perguntas, setPerguntas] = useState([]);
  const {exibirMensagem} = useMessageBox();
  const {verificar} = useLoader();
  const {validarPergunta} = useValidacoes();

  if(id){
    useEffect(() => {
      api.get("/perguntas/quiz/"+id)
      .then((resp) => {
        verificar(resp.data);
        setTimeout(() => {
          setPerguntas(resp.data)
        }, 200);
      })
      .catch((error) => {
        exibirMensagem(
          '',
          error.response.data.message,
          1
        );
      });
    }, [perguntas])
  }

  const salvarPergunta = (id, pergunta) => {
    if(validarPergunta(pergunta)){
      api.post("/perguntas/"+id, {
        questao: pergunta.questao,
        alternativas: pergunta.alternativas,
        alternativaCorreta: pergunta.alternativaCorreta
      })
      .then(() => {
        exibirMensagem(
          "/meuQuiz/"+id,
          "Pergunta salva com sucesso!",
          0
        );
      })
      .catch((error) => {
        exibirMensagem(
          '',
          error.response.data.message,
          1
        );
      });
    }
  }

  const excluirPergunta = (codigoQuiz, codigoPergunta) => {
    api.delete("/perguntas/"+codigoQuiz+"/"+codigoPergunta)
    .then(() => {
      exibirMensagem(
        '',
        "Pergunta deletada com sucesso",
        0
      );
    })
    .catch((error) => {
      exibirMensagem(
        '',
        error.response.data.message,
        1
      );
    })
  }

  return {perguntas, salvarPergunta,  excluirPergunta};
}

export default useMinhasPerguntas;