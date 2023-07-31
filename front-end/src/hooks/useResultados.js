import { useLocation } from "react-router-dom";

const useResultados = () => {

  const location = useLocation();
  let resultado = (location.state) && location.state.resultado;

  return {resultado};
}

export default useResultados;