import { useParams, Link } from "react-router-dom"
import Container from "../../components/Container"
import styles from './MeuQuiz.module.css';
import useMinhasPerguntas from "../../hooks/useMinhasPerguntas";
import BotaoVoltar from '../../components/BotaoVoltar';
import useSession from '../../hooks/useSession';

import imgLixo from '../../assets/iconLixo.png';
import useLoader from "../../hooks/useLoader";
import Loader from "../../components/Loader";

export default function MeuQuiz(){

  const {id} = useParams();
  const {codigo} = useSession();
  const {perguntas, excluirPergunta} = useMinhasPerguntas(id);
  const {loader} = useLoader();

  return(
    <Container>
      <BotaoVoltar
        caminho={"/meusQuizzes/"+codigo}
      />

      <header className={styles.botoes}>
        <Link to={"/criarPergunta/"+id} className={styles.btnAdcionar}>Adcionar pergunta</Link>
      </header>

      <div className={styles.perguntas}>
        {perguntas.length ? (
          perguntas.map((pergunta, index) => (
            <div key={pergunta.codigo} className={styles.pergunta}>
              <div className={styles.margemPergunta}>
                <div className={styles.divisor}>
                  <p className={styles.questao}>{index+1} - {pergunta.questao}</p>

                  {pergunta.alternativas.map((alternativa, index) => (
                    <p key={index} className={styles.alternativa +" "+styles[(index == pergunta.alternativaCorreta - 1) && "correta"]}>{["a", "b", "c", "d"][index]}) {alternativa}</p>
                  ))}
                </div>

                <div className={styles.divisor}>
                    <button className={styles.btnExcluir} onClick={() => excluirPergunta(id, pergunta.codigo)}>
                      <img src={imgLixo} width="20px" />
                    </button>
                </div>
              </div>
            </div>
          ))
        ) 
        : loader ? <Loader/>  
        : <h1 className="aviso">Sem perguntas</h1>}
      </div>
    </Container>
  )
}