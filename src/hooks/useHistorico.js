import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import api from '../services/api';
import useLoader from "./useLoader";

const useHistorico = () =>{

  const {id} = useParams();
  const [historico, setHistorico] = useState([]);
  const {verificar} = useLoader();

  useEffect(() =>{
    api.get("/usuarios/"+id)
    .then((resp) => {
      verificar(resp.data.historicoDeResultados);
      setTimeout(() => {
        setHistorico(resp.data.historicoDeResultados);
      }, 250);
    })
    .catch((error) => console.log(error.response.data.message))
  }, [historico])

  const limparHistorico = () =>{
    api.delete("/historicos/"+id)
    .then((resp) => console.log(resp))
    .catch((error) => console.log(error));
  }

  return {historico, limparHistorico};
}

export default useHistorico;