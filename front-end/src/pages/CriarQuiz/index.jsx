import Container from "../../components/Container"
import useMeuQuiz from "../../hooks/useMeuQuiz";
import FormQuiz from "../../components/FormQuiz";
import { Link } from "react-router-dom";
import styles from './CriarQuiz.module.css';
import useSession from "../../hooks/useSession";
import BotaoVoltar from "../../components/BotaoVoltar";

export default function CriarQuiz(){

  const {codigo} = useSession();
  const {salvarQuiz} = useMeuQuiz();

  return(
    <Container centralizar={true}>
      <BotaoVoltar
        caminho={"/meusQuizzes/"+codigo}
      />

      <FormQuiz
        executarAcao={salvarQuiz}
        txtBotao={"Criar"}
      />
    </Container>
  )
}