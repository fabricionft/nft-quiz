import Container from "../../components/Container";
import styles from './Quiz.module.css';
import useQuiz from "../../hooks/useQuiz";
import BotaoVoltar from '../../components/BotaoVoltar';

import iconreset from '../../assets/iconReset.png';

export default function Quiz(){

  const {iniciar, perguntas, indice, iniciarJogo, resetarJogo, responderQuestao} = useQuiz();

  return(
    <Container centralizar={true}>
      <BotaoVoltar
        caminho={"/quizzes"}
      />

      {
        !iniciar ? (
          <>
            <div className={styles.aviso}>
              <div className={styles.margemAviso}>
                <strong>Atenção</strong>
                <p>Só será adcionado ao seu histórico o resultado deste quiz, caso ele ainda não exista no mesmo!!</p>
              </div>
            </div>
            <button className={styles.btnQuiz} onClick={iniciarJogo}>Iniciar</button>
          </>
        ) : perguntas[indice] && (
          <div className={styles.quiz}>
            <div className={styles.margemQuiz}>
              <p className={styles.indice}>{indice+1}/{perguntas.length}</p>

              <div className={styles.pergunta}>
                <p className={styles.texto}>{perguntas[indice].questao}</p>
              </div>

              <div className={styles.alternativas}>
                {perguntas[indice].alternativas.map((alternativa, index) => (
                  <div key={index} className={styles.alternativa} onClick={() => responderQuestao(index)}>
                    <p className={styles.texto}>{["a", "b", "c", "d"][index]})&nbsp;{alternativa}</p>
                  </div>
                ))}
              </div>

              <button className={styles.btnReset} onClick={resetarJogo}>
                <img src={iconreset} className={styles.icon} />
              </button>
            </div>
          </div>
        )
      }
    </Container>
  )
}