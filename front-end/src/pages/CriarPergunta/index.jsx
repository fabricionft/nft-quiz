import { useParams } from "react-router-dom";
import Container from "../../components/Container";
import FormPergunta from "../../components/FormPergunta";
import useMinhasPerguntas from "../../hooks/useMinhasPerguntas";
import BotaoVoltar from '../../components/BotaoVoltar';

export default function CriarPergunta(){

  const {salvarPergunta} = useMinhasPerguntas();
  const {id} = useParams();

  return(
    <Container centralizar={true}>
      <BotaoVoltar caminho={"/meuQuiz/"+id}/>

      <FormPergunta
        executarAcao={salvarPergunta}
        idQuiz={id}
      />
    </Container>
  )
}