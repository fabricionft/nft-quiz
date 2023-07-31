import { useContext} from "react";
import { MessageBoxContext } from "../contexts/MessageBoxContext";
import { useLocation, useNavigate } from "react-router-dom";

const useMessageBox = () =>{

  const{visible, exibir, esconder} = useContext(MessageBoxContext);
  const navigate = useNavigate();
  const location = useLocation();

  const exibirMensagem = (destino, msg, type) => {  
    navigate((destino) ? destino : location.pathname, {
      state: {
        msg: msg,
        type: ["sucess", "error"][type],
        txtBotao:  ["Prosseguir", "Tentar novamente" ][type]
      }
    })
    exibir();
  }
  
  let dados = (location.state) && [
    location.state.type,
    location.state.msg,
    location.state.txtBotao
  ]

  return{visible, dados, esconder, exibirMensagem};
}

export default useMessageBox;