import api from "../services/api"
import { useNavigate } from "react-router-dom"
import useSession from "./useSession";
import useMessageBox from "./useMessageBox";

const useUsuario = () => { 

  const navigate = useNavigate();
  const {logar} = useSession();
  const {exibir} = useMessageBox();

  const cadastrar = (usuario) =>{
    console.log(usuario)

    api.post("/usuarios", {
      usuario: usuario.usuario,
      senha: usuario.senha
    })
    .then(() => {
      navigate("/",
      {state:{
        msg: "Usuário cadastrado com sucesso sucesso",
        type: "sucess",
        txtBotao: "Prosseguir"
      }});
      exibir();
    })
    .catch((error) => {
      navigate("/cadastro", 
      {state:{
        msg: error.response.data.message,
        type: "error",
        txtBotao: "Tentar novamente"
      }});
      exibir();
    })
  }

  const fazerLogin = (usuario) =>{
    api.post("/usuarios/login", {
      usuario: usuario.usuario,
      senha: usuario.senha
    })
    .then((res) => {
      navigate("/",
      {state:{
        msg: "Logado com sucesso",
        type: "sucess",
        txtBotao: "Prosseguir"
      }});
      logar(res.data);
      exibir();
    })
    .catch((error) => {
      navigate("/login",
      {state:{
        msg: error.response.data.message,
        type: "error",
        txtBotao: "Tentar novamente"
      }});
      exibir();
    })
  }

  return {cadastrar, fazerLogin};
}

export default useUsuario;