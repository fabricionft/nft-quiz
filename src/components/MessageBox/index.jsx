import styles from './MessageBox.module.css';
import useMessageBox from '../../hooks/useMessageBox';

export default function MessageBox(){

  const {visible, esconder, msg, type, txtBotao} = useMessageBox();
  
  return(
    <>
      {visible && (
        <div className={styles.containerMessageBox}>
          <div className={styles.messageBox +" "+styles[type]}>
            <div className={styles.margemMessageBox}>
              <p className={styles.textoMessageBox}>
                {msg}
              </p>
    
              <button type='button' onClick={esconder} className={styles.fecharMessageBox}>
                {txtBotao}
              </button>
            </div>
          </div>
        </div>
      )}
    </>
  )
}