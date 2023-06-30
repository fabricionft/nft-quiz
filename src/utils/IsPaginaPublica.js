import AppRoutes from "../constant/AppRoutes";
import { useLocation } from "react-router-dom";

const isPaginaPublica = () =>{
  const location = useLocation();
  const rotasPublicas = Object.values(AppRoutes.public);
  return rotasPublicas.includes(location.pathname);
}

export default isPaginaPublica;