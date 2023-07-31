import Container from "../../components/Container";
import Formulario from "../../components/FormUsuario";
import useUsuario from "../../hooks/useUsuario";
import useRotas from "../../hooks/useRotas";

export default function Login(){

  const {fazerLogin} = useUsuario();
  const {bloquearRotaPublica} = useRotas();
  
  bloquearRotaPublica();

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