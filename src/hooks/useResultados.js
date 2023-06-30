import { useLocation } from "react-router-dom";

const useResultados = () => {

  const location = useLocation();
  let resultado;

  if(location.state){
    resultado = location.state.resultado;
  }

  return {resultado};
}

export default useResultados;