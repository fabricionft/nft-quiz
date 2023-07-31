import { useEffect, useState } from "react";
import api from "../services/api";
import useLoader from '../hooks/useLoader';
import useMessgaBox from './useMessageBox';

const useQuizzes = () => {

  const [quizzes, setQuizzes] = useState([]);
  const [tag, setTag] = useState("");
  const {verificar} = useLoader();
  const {exibirMensagem} = useMessgaBox();

  const inserirTag = (tag) => setTag(tag);

  useEffect(() => {
    api.get("/quizzes")
    .then((resp) => {
      verificar(resp.data);
      setTimeout(() => {
        setQuizzes(resp.data)
      }, 200)
    })
    .catch((error) => {
      exibirMensagem(
        '',
        error.response.data.message,
        1
      );
    })
  }, [quizzes])

  return{quizzes, tag, inserirTag};
}

export default useQuizzes;