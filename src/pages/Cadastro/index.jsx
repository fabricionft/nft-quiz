import Container from '../../components/Container';
import Form from '../../components/FormUsuario';
import useUsuario from '../../hooks/useUsuario';

export default function Cadastro(){

  const {cadastrar} = useUsuario();

  return(
    <Container centralizar={true}>
      <Form
        executarAcao={cadastrar}
        txtBotao={"Cadastrar"}
        redirecionar={"/login"}
        prefixo={"Já"}
      />
    </Container>
  )
}