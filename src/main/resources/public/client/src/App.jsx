import { useState } from 'react'
import './App.css'
import CourseComponent from './components/CourseComponent'

function App() {
  const [count, setCount] = useState(0)

  return (
    <h1 className="text-3xl font-bold underline">
      Hello world!
      <div>{count}</div>
      <button className="bg-yellow-300 rounded-lg p-5" onClick={() => setCount(count + 1)}>Click me</button>
      <CourseComponent />
    </h1>

  )
}

export default App
