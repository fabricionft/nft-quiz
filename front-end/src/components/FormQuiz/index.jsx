import { useState, useEffect } from 'react';
import styles from './FormQuiz.module.css';
import api from '../../services/api';

export default function FormQuiz({ id, executarAcao, txtBotao}){

  const [quiz, setQuiz] = useState({});

  if(id){
    useEffect(() =>{
      api.get("/quizzes/"+id)
      .then(resp => setQuiz(resp.data))
      .catch((error) => console.log(error))
    }, [id])
  }

  const preencherQuiz = (e) =>{
    setQuiz({...quiz, [e.target.name] : e.target.value})
  }

  const enviarFormulario = (e) =>{
    e.preventDefault();
    executarAcao(quiz)
  }

  return(
    <form onSubmit={enviarFormulario} className={styles.formQuiz}>
        <div className={styles.margemFormQuiz}>
          <label >Nome</label>
          <input className={styles.inputQuiz} type="text" placeholder="Digite o nome"
            name="nome"
            value={quiz.nome || ""}
            onChange={preencherQuiz}
          />

          <label >Tema</label>
          <input className={styles.inputQuiz} type="text" placeholder="Digite o tema"
            name="tema"
            value={quiz.tema || ""}
            onChange={preencherQuiz}
          />

          <label >Descrição</label>
          <textarea className={styles.descricao} placeholder="Digite a descrição"
            name="descricao"
            value={quiz.descricao || ""}
            onChange={preencherQuiz}
          ></textarea>

          <button className={styles.btnQuiz}>{txtBotao}</button>
        </div>
      </form>
  )
}