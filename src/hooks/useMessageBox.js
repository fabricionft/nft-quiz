import { useContext } from "react";
import { MessageBoxContext } from "../context/MessageBoxContext";
import { useLocation } from "react-router-dom";

const useMessageBox = () =>{
  const{visible, exibir, esconder} = useContext(MessageBoxContext);
  const location = useLocation();
  
  let msg = '';
  let type = '';
  let txtBotao = '';
  if(location.state){
    msg = location.state.msg;
    type = location.state.type;
    txtBotao = location.state.txtBotao;
  }

  return{visible, exibir, esconder, msg, type, txtBotao};
}

export default useMessageBox;