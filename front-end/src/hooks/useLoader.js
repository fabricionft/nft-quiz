import { useContext } from "react";
import { LoaderContext } from "../contexts/LoaderContext";

const useLoader = () => {
  
  const{loader, verificar} = useContext(LoaderContext);
  return{loader, verificar};
}

export default useLoader;