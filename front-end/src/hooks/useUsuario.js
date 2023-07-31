import api from "../services/api"
import useSession from "./useSession";
import useMessageBox from "./useMessageBox";
import useValidacoes from "./useValidacoes";

const useUsuario = () => { 

  const {logar} = useSession();
  const {exibirMensagem} = useMessageBox();
  const {validarUsuario} = useValidacoes();

  const cadastrar = (usuario) =>{
    if(validarUsuario(usuario)){
      api.post("/usuarios", {
        usuario: usuario.usuario.trim(),
        senha: usuario.senha.trim()
      })
      .then(() => {
        fazerLogin(usuario, "Conta criada com sucesso, você ja foi automaticamente logado na mesma!");
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

  const fazerLogin = (usuario, msg) =>{
    if(validarUsuario(usuario)){
      api.post("/usuarios/login", {
        usuario: usuario.usuario.trim(),
        senha: usuario.senha.trim()
      })
      .then((resp) => {
        logar(resp.data);
        exibirMensagem(
          "/",
          (msg) ? msg : "Logado com sucesso",
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

  return {cadastrar, fazerLogin};
}

export default useUsuario;