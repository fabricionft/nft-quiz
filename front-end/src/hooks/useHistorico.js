import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import api from '../services/api';
import useLoader from "./useLoader";
import useMessageBox from "./useMessageBox";

const useHistorico = () =>{

  const {id} = useParams();
  const [historico, setHistorico] = useState([]);
  const {verificar} = useLoader();
  const {exibirMensagem} = useMessageBox();

  useEffect(() =>{
    api.get("/historicos/usuario/"+id)
    .then((resp) => {
      verificar(resp.data);
      setTimeout(() => {
        setHistorico(resp.data);
      }, 200);
    })
    .catch((error) => {
      exibirMensagem(
        '',
        error.response.data.message,
        1
      );
    })
  }, [historico])

  const limparHistorico = () =>{
    api.delete("/historicos/"+id)
    .then(() => {
      exibirMensagem(
        '',
        "Histórico excluído com sucesso",
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

  return {historico, limparHistorico};
}

export default useHistorico;
