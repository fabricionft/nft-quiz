import { useState } from 'react';
import styles from './FormUsuario.module.css'
import { Link } from 'react-router-dom';

export default function FormUsuario({executarAcao, txtBotao, prefixo, redirecionar}){

  const [usuario, setUsuario] = useState({});

  const criarUsuario = (e) => {
    setUsuario({...usuario, [e.target.name] : e.target.value});
  }

  const enviarFormulario = (e) =>{
    e.preventDefault();
    executarAcao(usuario);
  }

  return(
    <form onSubmit={enviarFormulario} className={styles.form}>
      <div className={styles.margemForm}>
        <input className={styles.inputFormUsuario} type="text" placeholder='Digite o usuário'
          name='usuario'
          onChange={criarUsuario}
        />
        <input className={styles.inputFormUsuario}type="password" placeholder='Digite a senha'
          name='senha'
          onChange={criarUsuario}
        />

        <button className={styles.btnFormUsuario}>{txtBotao}</button>

        <Link to={redirecionar} className={styles.redirecionar}>{prefixo} tem conta?</Link>
      </div>
    </form>
  )
}