import { useParams } from 'react-router-dom';
import api from '../services/api';
import { useEffect, useState } from 'react';
import useLoader from './useLoader';
import useMessageBox from './useMessageBox';

const useRanking = () => {

  const {id} = useParams();
  const {verificar} = useLoader();
  const [ranking, setRanking] = useState([]);
  const {exibirMensagem} = useMessageBox();

  useEffect(() => {
    api.get("/ranking/quiz/"+id)
    .then((resp) => {
      verificar(resp.data);
      setTimeout(() => {
        setRanking(resp.data)
      }, 200)
    })
    .catch((error) => {
      exibirMensagem(
        '',
        error.response.data.message,
        1
      );
    });
  }, [ranking])

  return {id, ranking};
}

export default useRanking;