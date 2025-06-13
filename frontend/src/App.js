import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [mensaje, setMensaje] = useState('');
  const [nombre, setNombre] = useState('');
  const [saludo, setSaludo] = useState('');
  const [todosSaludos, setTodosSaludos] = useState([]);
  const [totalRegistros, setTotalRegistros] = useState(0);

  useEffect(() => {
    // Obtener status del backend
    axios.get('/api/status')
      .then(response => {
        console.log('Status del backend:', response.data);
        setMensaje(`✅ ${response.data.database}`);
        setTotalRegistros(response.data.totalRegistros);
      })
      .catch(error => {
        console.error('Error:', error);
        setMensaje('❌ Error al conectar con el backend');
      });
    
    // Cargar todos los saludos existentes
    cargarSaludos();
  }, []);

  const cargarSaludos = () => {
    axios.get('/api/saludos')
      .then(response => {
        console.log('Saludos cargados:', response.data);
        setTodosSaludos(response.data.saludos || []);
      })
      .catch(error => {
        console.error('Error cargando saludos:', error);
      });
  };

  const enviarSaludo = () => {
    if (nombre.trim()) {
      axios.get(`/api/saludo/${nombre}`)
        .then(response => {
          console.log('Respuesta del saludo:', response.data);
          setSaludo(response.data.saludo);
          setTotalRegistros(response.data.totalSaludos);
          
          // Recargar la lista de saludos
          cargarSaludos();
          
          // Limpiar el campo
          setNombre('');
        })
        .catch(error => {
          console.error('Error:', error);
          setSaludo('Error al enviar saludo');
        });
    }
  };

  const handleKeyPress = (e) => {
    if (e.key === 'Enter') {
      enviarSaludo();
    }
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>🚀 TP5 - Docker Full Stack con PostgreSQL</h1>
        <h2>Spring Boot + React + PostgreSQL + Docker</h2>
        
        <div className="status-section">
          <p><strong>Estado:</strong> {mensaje}</p>
          <p><strong>Total de saludos en BD:</strong> {totalRegistros}</p>
        </div>

        <div className="interaction-section">
          <h3>💬 Enviar Saludo (se guarda en PostgreSQL)</h3>
          <div>
            <input 
              type="text" 
              value={nombre}
              onChange={(e) => setNombre(e.target.value)}
              onKeyPress={handleKeyPress}
              placeholder="Ingresa tu nombre"
            />
            <button onClick={enviarSaludo}>Saludar y Guardar</button>
          </div>
          {saludo && <p><strong>Respuesta:</strong> {saludo}</p>}
        </div>

        {todosSaludos.length > 0 && (
          <div className="database-section">
            <h3>📊 Datos desde PostgreSQL</h3>
            <div className="saludos-list">
              {todosSaludos.slice(-5).reverse().map((item, index) => (
                <div key={item.id} className="saludo-item">
                  <p><strong>#{item.id}</strong> - {item.nombre}: {item.mensaje}</p>
                  <small>📅 {new Date(item.fechaCreacion).toLocaleString()}</small>
                </div>
              ))}
            </div>
            {todosSaludos.length > 5 && (
              <p><em>Mostrando los últimos 5 de {todosSaludos.length} saludos</em></p>
            )}
          </div>
        )}

        <div className="services-info">
          <h3>🐳 Servicios Docker Activos</h3>
          <ul>
            <li>🖥️ Frontend: React (Puerto 3000)</li>
            <li>⚙️ Backend: Spring Boot (Puerto 8080)</li>
            <li>🗄️ Base de Datos: PostgreSQL (Puerto 5432)</li>
            <li>🔧 PgAdmin: Administrador BD (Puerto 8081)</li>
            <li>📦 Redis: Cache (Puerto 6379)</li>
          </ul>
        </div>
      </header>
    </div>
  );
}

export default App;