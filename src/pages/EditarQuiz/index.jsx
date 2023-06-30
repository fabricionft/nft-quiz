import Container from "../../components/Container"
import useMeuQuiz from "../../hooks/useMeuQuiz";
import FormQuiz from "../../components/FormQuiz";
import { useParams } from "react-router-dom";
import BotaoVoltar from '../../components/BotaoVoltar';
import useSession from "../../hooks/useSession";

export default function EditarQuiz(){

  const {id} = useParams();
  const {codigo} = useSession();
  const {editarQuiz} = useMeuQuiz();

  return(
    <Container centralizar={true}>
      <BotaoVoltar
        caminho={"/meusQuizzes/"+codigo}
      />

      <FormQuiz
        id={id}
        executarAcao={editarQuiz}
        txtBotao={"Editar"}
      />
    </Container>
  )
}