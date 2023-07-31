import { useState } from 'react';
import styles from './FormPergunta.module.css';

export default function FormPergunta({executarAcao, idQuiz}){

  const [pergunta, setPergunta] = useState({});

  const preencherPergunta = (e) => setPergunta({...pergunta, [e.target.name] : e.target.value});
  const preencherAlternativaCorreta = (e) => setPergunta({...pergunta, [e.target.name] : parseInt(e.target.selectedIndex)})
  
  const enviar = (e) =>{
    e.preventDefault();
    let obj = {
      questao: pergunta.questao,
      alternativas: [pergunta.alt_1, pergunta.alt_2, pergunta.alt_3, pergunta.alt_4],
      alternativaCorreta: pergunta.alternativaCorreta
    }

    executarAcao(idQuiz, obj);
  }

  return(
    <form onSubmit={enviar} className={styles.formPergunta}>
      <div className={styles.margemFormPergunta}>
        <div className={styles.divisor1}>
          <label>Questão</label>
          <textarea className={styles.textArea} placeholder='Digite a questão'
            name="questao"
            onChange={preencherPergunta}
          ></textarea>
        </div>

        <div className={styles.divisor2}>
          <label>Alternativa a</label>
          <textarea className={styles.textArea} placeholder='Digite a alternativa a'
            name="alt_1"
            onChange={preencherPergunta}
          ></textarea>
        </div>

        <div className={styles.divisor2}>
          <label>Alternativa b</label>
          <textarea className={styles.textArea} placeholder='Digite a alternativa b'
            name="alt_2"
            onChange={preencherPergunta}
          ></textarea>
        </div>

        <div className={styles.divisor2}>
          <label>Alternativa c</label>
          <textarea className={styles.textArea} placeholder='Digite a alternativa c'
            name="alt_3"
            onChange={preencherPergunta}
          ></textarea>
        </div>

        <div className={styles.divisor2}>
          <label>Alternativa d</label>
          <textarea className={styles.textArea} placeholder='Digite a alternativa d'
            name="alt_4"
            onChange={preencherPergunta}
          ></textarea>
        </div>

        <div className={styles.divisor1}>
          <label>Alternativa correta</label>
          <select name='alternativaCorreta' onChange={preencherAlternativaCorreta}> 
            <option value="">Escolha</option>
            <option value="1">a</option>
            <option value="2">b</option>
            <option value="3">c</option>
            <option value="4">d</option>
          </select>
        </div>

        <button className={styles.btnAdcionarPergunta}>Adcionar</button>
      </div>
    </form>
  )
}