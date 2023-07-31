import { useContext } from "react";
import { SessionContext } from "../contexts/SessionContext";

const useSession = () => {

  const {sessao, codigo, token, deslogar, logar} = useContext(SessionContext);
  return {sessao, codigo, token, deslogar, logar};
}

export default useSession;