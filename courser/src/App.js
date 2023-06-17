import { useState } from 'react'
import './App.css'
import CourseComponent from './components/CourseComponent.jsx'
// import axios from 'axios';

function App() {
  const [count, setCount] = useState(0)

  return (
    <div>
      <div className='flex mx-auto max-w-4xl border-4 border-yellow-300 '

      >CHANGE! WAR CHECK</div>
      <h1 className="text-3xl font-bold underline">

        <div>{count}</div>
        <button className="bg-yellow-300 rounded-lg p-5" onClick={() => setCount(count + 1)}>Click me</button>
        <CourseComponent />
      </h1>
    </div>

  )
}

export default App
