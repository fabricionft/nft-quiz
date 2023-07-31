import styles from './MenuBar.module.css';
import {Link} from 'react-router-dom';
import useSession from '../../hooks/useSession';

import imgCasa from '../../assets/iconCasa.png';
import imgFechar from '../../assets/iconX.png';
import imgEngrenagem from '../../assets/iconEngrenagem.png';
import imgHistorico from '../../assets/historico.png';
import imgQuiz from '../../assets/quiz.png';

export default function MenuBar(){

  const {sessao, codigo, deslogar} = useSession();

  const fecharMenuBar = () => document.getElementById('menu').checked = false;
  
  return(
    <>
      <input type="checkbox" id="menu" className={styles.aciona}/>

      <div className={styles.sobreporBarraMenu}></div>

      <div className={styles.barraMenu}>
        <div className={styles.margemBarraMenu}>
          <div className={styles.detalhesLogin}>
            {sessao ? <p className={styles.textoLink} onClick={deslogar}>Sair</p> 
            : (
                <>
                  <Link to={"/login"} onClick={fecharMenuBar}>
                    <span className={styles.texto}><p className={styles.textoLink}>Faça login</p> ou</span>
                  </Link>

                  <Link to={"/cadastro"} onClick={fecharMenuBar}>
                    <p className={styles.textoLink}>Cadastre-se</p>
                  </Link>
                </>
              )
            }
          </div>

          <div className={styles.botoesBarraMenu}>
            <Link to={"/"} onClick={fecharMenuBar} className={styles.botao}>
              <div className={styles.imagemBotao}>
                <img src={imgCasa} className={styles.icon}/>
              </div>

              <div className={styles.textoBotao}>
                <p className={styles.texto}>Home</p>
              </div>
            </Link>

            {sessao && 
              (
                <>
                  <Link to={"/quizzes"} onClick={fecharMenuBar} className={styles.botao}>
                    <div className={styles.imagemBotao}>
                      <img src={imgQuiz} className={styles.icon}/>
                    </div>

                    <div className={styles.textoBotao}>
                      <p className={styles.texto}>Quizzes</p>
                    </div>
                  </Link>

                  <Link to={"/meusQuizzes/"+codigo} onClick={fecharMenuBar} className={styles.botao}>
                    <div className={styles.imagemBotao}>
                      <img src={imgEngrenagem} className={styles.icon}/>
                    </div>

                    <div className={styles.textoBotao}>
                      <p className={styles.texto}>Meus quizzes</p>
                    </div>
                  </Link>

                  <Link to={"/historico/"+codigo} onClick={fecharMenuBar} className={styles.botao}>
                    <div className={styles.imagemBotao}>
                      <img src={imgHistorico} className={styles.icon}/>
                    </div>

                    <div className={styles.textoBotao}>
                      <p className={styles.texto}>Historico</p>
                    </div>
                  </Link>
                </>
              )
            }
          </div>
        </div>

        <label htmlFor="menu">
          <img src={imgFechar} className={styles.fechar}/>
        </label>
      </div>
    </>
  )
}