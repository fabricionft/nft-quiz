import styles from './MessageBox.module.css';
import useMessageBox from '../../hooks/useMessageBox';

export default function MessageBox(){

  const {visible, esconder, dados} = useMessageBox();
  
  return(
    <>
      {visible && (
        <div className={styles.containerMessageBox}>
          <div className={styles.messageBox +" "+styles[dados[0]]}>
            <div className={styles.margemMessageBox}>
              <p className={styles.textoMessageBox}>
                {dados[1]}
              </p>
    
              <button type='button' onClick={esconder} className={styles.fecharMessageBox}>
                {dados[2]}
              </button>
            </div>
          </div>
        </div>
      )}
    </>
  )
}