import { useEffect, useState } from "react";
import api from "../services/api";
import useLoader from '../hooks/useLoader';

const useQuizzes = () => {

  const [quizzes, setQuizzes] = useState([]);
  const {verificar} = useLoader();

  useEffect(() => {
    api.get("/quizzes")
    .then((resp) => {
      verificar(resp.data);
      setTimeout(() => {
        setQuizzes(resp.data)
      }, 250)
    })
    .catch((error) => console.log(error))
  }, [quizzes])

  return{quizzes};
}

export default useQuizzes;