import ReactDOM from 'react-dom/client'
import App from './App'
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import './index.css'

//Paginas
import ErrorPage from './pages/ErrorPage';
import Home from './pages/Home';
import Cadastro from './pages/Cadastro';
import Login from './pages/Login';
import Quizzes from './pages/Quizzes';
import Quiz from './pages/Quiz';
import Resultados from './pages/Resultados';
import MeusQuizzes from './pages/MeusQuizzes';
import MeuQuiz from './pages/MeuQuiz';
import Ranking from './pages/Ranking';
import CriarQuiz from './pages/CriarQuiz';
import EditarQuiz from './pages/EditarQuiz';
import CriarPergunta from './pages/CriarPergunta';
import Historico from './pages/Historico';

//Contextos
import { Sessionprovider } from './contexts/SessionContext';
import { MessageBoxProvider } from './contexts/MessageBoxContext';
import { LoaderProvider } from './contexts/LoaderContext';

const router = createBrowserRouter([
  {
    path: "/",
    element: <App/>,
    errorElement: <ErrorPage/>,
    children: [
      {
        path: "/",
        element: <Home/>,
      },
      {
        path: "/cadastro",
        element: <Cadastro/>,
      },
      {
        path: "/login",
        element: <Login/>,
      },
      {
        path: "/quizzes",
        element: <Quizzes/>,
      },
      {
        path: "/quiz/:id",
        element: <Quiz/>,
      },
      {
        path: "/resultados",
        element: <Resultados/>,
      },
      {
        path: "/meusQuizzes/:id",
        element: <MeusQuizzes/>
      },
      {
        path: "/meuQuiz/:id",
        element: <MeuQuiz/>
      },
      {
        path: "/ranking/:id",
        element: <Ranking/>
      },
      {
        path: "/criarQuiz",
        element: <CriarQuiz/>
      },
      {
        path: "/editarQuiz/:id",
        element: <EditarQuiz/>
      },
      {
        path: "/criarPergunta/:id",
        element: <CriarPergunta/>
      },
      {
        path: "/historico/:id",
        element: <Historico/>,
      },
    ]
  }
])

ReactDOM.createRoot(document.getElementById('root')).render(
  <Sessionprovider>
    <MessageBoxProvider>
      <LoaderProvider>
        <RouterProvider router={router}/>
      </LoaderProvider>
    </MessageBoxProvider>
  </Sessionprovider>
)
