import { useNavigate } from "react-router-dom";
import Container from "../../components/Container";
import Formulario from "../../components/FormUsuario";
import useUsuario from "../../hooks/useUsuario";
import useSession from "../../hooks/useSession";
import { useEffect } from "react";

export default function Login(){

  const {sessao} = useSession();
  const navigate = useNavigate();
  const {fazerLogin} = useUsuario();

  useEffect(() => {
    if(sessao) navigate("/");
  }, [sessao])

  return(
    <Container centralizar={true}>
      <Formulario 
        executarAcao={fazerLogin}
        txtBotao={"Login"}
        redirecionar={"/cadastro"}
        prefixo={"Não"}
      />
    </Container>
  )
}