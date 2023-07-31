import Container from "../../components/Container"
import BotaoVoltar from '../../components/BotaoVoltar'
import useResultados from "../../hooks/useResultados";
import styles from './Resultados.module.css';
import NotaResultado from "../../components/NotaResultado";

import iconCorret from '../../assets/correct.png';
import iconErro from '../../assets/letra-x.png';

export default function Resultados(){

  const {resultado} = useResultados();

  return(
    <Container centralizar={true}>
      <BotaoVoltar
        caminho={"/quizzes"}
      />

      <div className={styles.containerResultados}>
        <NotaResultado
          porcentagem={resultado.porcentagemAcertos}
        />

        <div className={styles.margemResultados}>
          <p className={styles.tituloResultados}>Resultado</p>

          <div className={styles.avaliacao}>
            <div className={styles.linhaIcon}>
              <div className={styles.acertoIcon}>
                <img src={iconCorret} className={styles.iconAcertosErros} />
              </div>

              <div className={styles.erroIcon}>
                <img src={iconErro} className={styles.iconAcertosErros} />
              </div>
            </div>  

            <div className={styles.linha}>
              <div className={styles.acerto}>
                <p className={styles.quantidadeAcertos}>{resultado.acertos}/{resultado.quantidadeDeQuestoes}</p>
              </div>

              <div className={styles.erro}>
                <p className={styles.quantidadeErros}>{resultado.erros}/{resultado.quantidadeDeQuestoes}</p>
              </div>
            </div>

            <div className={styles.linha}>
              <div className={styles.acerto}>
                <p className={styles.quantidadeAcertos}>{(resultado.porcentagemAcertos).toFixed(1)}%</p>
              </div>

              <div className={styles.erro}>
                <p className={styles.quantidadeErros}>{(100 - resultado.porcentagemAcertos).toFixed(1)}%</p>
              </div>
            </div>
          </div>

          {resultado.respostas.map((resposta, index) => (
            <div key={index} className={styles.respostas}>
              <p className={styles.textoQuestoes}>Questão {index+1}</p>
              <img src={(resposta == 'c' ? iconCorret : iconErro)} className={styles.iconAcertosErros} />
            </div>
          ))}
        </div>
      </div>
    </Container>
  );
}