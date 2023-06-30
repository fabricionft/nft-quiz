import styles from './Quiz.module.css';
import { Link } from 'react-router-dom';
import useMeuQuiz from '../../hooks/useMeuQuiz';
import Card from '../Card';

import imgLixo from '../../assets/iconLixo.png';
import imgEditar from '../../assets/iconEditar.png';
import imgQuestao from '../../assets/iconQuestao.png';

export default function Quizzes({quiz, meuQuiz}){

  const {excluirQuiz} = useMeuQuiz();

  return(
    <Card>
      <div className={styles.margemQuiz}>
        <div className={styles.tituloQuiz}>
          <h1 className={styles.titulo}>{quiz.nome}</h1>
          <p className={styles.tag}>#{quiz.tag}</p>
        </div>

        <div className={styles.descricao}>
          <div className={styles.margemDescricao}>
            <ul>
              <li className={styles.tema}>
                <p className={styles.textoTema}>{quiz.tema}</p>  
              </li>

              <li className={styles.tema}>
                <p className={styles.textoTema}>{quiz.quantidadeDePerguntas} pergunta{quiz.quantidadeDePerguntas == 1 ? "" : "s"}</p>  
              </li>
            </ul>

            <p>{quiz.descricao}</p>
          </div>
        </div>

        <div className={styles.botoesQuiz}>
          {meuQuiz ? (
            <div className={styles.acoesQuiz}>
              <Link to={"/editarQuiz/"+quiz.codigo} className={styles.btnEditar}>
                <img src={imgEditar} width="19px" />
              </Link>

              <Link to={"/meuQuiz/"+quiz.codigo} className={styles.btnPerguntas}>
                <img src={imgQuestao} width="27px" />
              </Link>

              <button onClick={() => excluirQuiz(quiz.codigo)} className={styles.btnExcluir}>
                <img src={imgLixo} width="24px" />
              </button>
            </div>
          ) : <Link to={"/quiz/"+quiz.codigo} className={styles.btnResponder}>Iniciar</Link>}
        </div>
      </div>
    </Card>
  )
}