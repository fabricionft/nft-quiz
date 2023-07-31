import { useLocation, useNavigate } from "react-router-dom"
import useSession from "./useSession";
import { useEffect } from "react";
import PublicRoutes from "../constants/PublicRoutes";

const useRotas = () => {

  const {sessao} = useSession();
  const navigate = useNavigate();
  const location = useLocation();

  const bloquearRotaPublica = () => {
    useEffect(() => {
      if(sessao) navigate("/")
    }, [sessao])
  }

  const bloquearRotaPrivada = () => {
    useEffect(() => {
      if(!sessao) navigate("/")
    }, [sessao])
  }

  const verificaSeAPaginaEPublica = () => {
    return PublicRoutes.includes(location.pathname);
  }

  return {bloquearRotaPublica, bloquearRotaPrivada, verificaSeAPaginaEPublica};
}

export default useRotas;