import Container from "../../components/Container";
import styles from './Historico.module.css';
import useHistorico from "../../hooks/useHistorico";
import Card from '../../components/Card';
import NotaResultado from "../../components/NotaResultado";
import useLoader from "../../hooks/useLoader";
import Loader from "../../components/Loader";

import iconRelogio from '../../assets/relogio.png';
import iconCalendario from '../../assets/calendario.png';
import iconCorret from '../../assets/correct.png';
import iconErro from '../../assets/letra-x.png';

export default function Historico(){
  
  const {historico, limparHistorico} = useHistorico();
  const {loader} = useLoader();

  return(
    <Container>
      {historico.length ? (
        <>
          <header className={styles.acoesHistorico}>
            <button className={styles.limparHistorico} onClick={limparHistorico}>Limpar histórico</button>
          </header>

          <div className={styles.containerHistorico}>
            {historico.slice().reverse().map(resultado => (
              <Card key={resultado.codigo}>
                <NotaResultado
                  porcentagem={resultado.porcentagemAcertos}
                />

                <div className={styles.margemResultado}>

                  <p className={styles.textoTag}>#{resultado.tagQuiz}</p>

                  <div className={styles.data}>
                    <img src={iconCalendario} className={styles.iconData}/>
                    {resultado.data.split("  ")[0]}
                  </div>

                  <div className={styles.data}>
                    <img src={iconRelogio} className={styles.iconData}/>
                    {resultado.data.split("  ")[1]}
                  </div>

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
              </Card>
            ))}
          </div>
        </>
      ) 
      : loader ? <Loader/>
      : <h1 className="aviso">Histórico vázio</h1>
    }
    </Container>
  )
}