import { useEffect, useState } from "react";
import api from "../services/api";
import { useParams } from "react-router-dom";
import useLoader from "./useLoader";
import useMessaBox from './useMessageBox';

const useMeusQuizzes = () => {
  
  const {id} = useParams();
  const {verificar} = useLoader();
  const {exibirMensagem} = useMessaBox();
  
  const [meusQuizzes, setMeusQuizzes] = useState([]);
  const [ordem, setOrdem] = useState('asc');
  let quizzesOrdenados = (ordem == 'desc') ? meusQuizzes : meusQuizzes.slice().reverse();

  const definirOrdem = () => setOrdem((ordem == 'asc') ? 'desc' : 'asc');

  useEffect(() => {
    api.get("/quizzes/usuario/"+id)
    .then((resp) => {
      verificar(resp.data)
      setTimeout(() => {
        setMeusQuizzes(resp.data);
      }, 200)
    })
    .catch((error) =>{
      exibirMensagem(
        '',
        error.response.data.message,
        1
      );
    });
  }, [meusQuizzes])

  return{quizzesOrdenados, definirOrdem};
}

export default useMeusQuizzes;