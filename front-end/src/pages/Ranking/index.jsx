import BotaoVoltar from '../../components/BotaoVoltar';
import Container from '../../components/Container';
import Loader from '../../components/Loader';
import useLoader from '../../hooks/useLoader';
import useRanking from '../../hooks/useRanking';
import useSession from '../../hooks/useSession';
import styles from './Ranking.module.css';

import imgInfo from '../../assets/info.png';

export default function Ranking(){

  const {codigo} = useSession();
  const {ranking} = useRanking();
  const {loader} = useLoader();

  return(
    <Container centralizar={true}>
      <BotaoVoltar
        caminho={(localStorage.getItem('paginaPassada') == "/quizzes") ? "/quizzes" : "/meusQuizzes/"+codigo}
      />
      
      {
        ranking.length ? (
          <div className={styles.containerRanking}>
            <div className={styles.margemRanking}>
              <h2 className={styles.tituloRanking}>Ranking</h2>

              <div className={styles.linha}>
                <div className={styles.posicao+" "+styles["legenda"]}>posição</div>

                <div className={styles.usuario+" "+styles["legenda"]}>usuario</div>

                <div className={styles.pontuacao+" "+styles["legenda"]}>acertos</div>
              </div>

              {ranking.map((item, index) => (
                <div key={item.codigo} className={styles.linha}>
                  <div className={styles.posicao+" "+styles[
                    index == 0 ? "ouro" :
                    index == 1 ? "prata" : 
                    index == 2 && "bronze"
                  ]}>
                    {index+1}º 
                  </div>

                  <div className={styles.usuario}>
                    {item.usuario}
                  </div>

                  <div className={styles.pontuacao}>
                    {item.quantidadeAcertos+"/"+item.quantidadePerguntas}
                  </div>
                </div>
              ))}

              <div className={styles.info}>
                <img src={imgInfo} className={styles.iconInfo} />
                <p className={styles.textoInfo}>O ranking contabiliza somente o <br/>primeiro resultado de cada usuário<br/>na atual versão do quiz</p>
              </div>
            </div>
          </div>
        )
        : loader ? <Loader/>
        : (
          <div className={styles.aviso}>
            <div className={styles.margemAviso}>
              Nenhum usuário respondeu a atual versão deste quiz até o momento!
            </div>
          </div>
        )
      }
    </Container>
  );
}