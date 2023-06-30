import { useEffect, useState } from "react";
import api from "../services/api";
import { useParams } from "react-router-dom";
import useLoader from "./useLoader";

const useMeusQuizzes = () => {
  
  const [meusQuizzes, setMeusQuizzes] = useState([]);
  const {exibirLoader, removerLoader, verificar} = useLoader();
  const {id} = useParams();

  useEffect(() => {
    api.get("/usuarios/"+id)
    .then((resp) =>{
      verificar(resp.data.quizzes)
      setTimeout(() => {
        setMeusQuizzes(resp.data.quizzes);
      }, 250)
    })
    .catch((error) => console.log(error));
  }, [meusQuizzes])

  return{meusQuizzes};
}

export default useMeusQuizzes;