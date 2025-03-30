'use client';

import { useState } from 'react';

export default function Notificador() {
  const [subject, setSubject] = useState('');
  const [message, setMessage] = useState('');
  const [isSending, setIsSending] = useState(false);
  const [notificationSent, setNotificationSent] = useState(false);
  const [error, setError] = useState('');

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    
    if (!subject.trim() || !message.trim()) {
      setError('Por favor, completa ambos campos');
      return;
    }
    
    setError('');
    setIsSending(true);
    setNotificationSent(false);
    
    try {
      const response = await fetch('/api/send-notification', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ 
          title: subject, 
          body: message 
        }),
      });

      if (!response.ok) {
        throw new Error('Error al enviar la notificación');
      }

      setNotificationSent(true);
      setSubject('');
      setMessage('');
    } catch (err) {
      setError('Error al enviar la notificación');
      console.error(err);
    } finally {
      setIsSending(false);
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-pink-100 via-purple-100 to-blue-100 py-12 px-4 sm:px-6 lg:px-8">
      <div className="max-w-md mx-auto bg-white rounded-3xl shadow-lg overflow-hidden md:max-w-2xl">
      <div className="p-8">
      <div className="text-center mb-6">
      <h1 className="text-3xl font-extrabold text-purple-600">Puella Notify!</h1>
      </div>
      
      <form onSubmit={handleSubmit} className="space-y-6">
      <div>
        <label htmlFor="subject" className="block text-sm font-medium text-gray-700">
        Asunto:
        </label>
        <input
        id="subject"
        type="text"
        value={subject}
        onChange={(e) => setSubject(e.target.value)}
        className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-purple-500 focus:border-purple-500 text-gray-900 placeholder-gray-500"
        placeholder="Asunto"
        required
        />
      </div>
      
      <div>
        <label htmlFor="message" className="block text-sm font-medium text-gray-700">
        Mensaje:
        </label>
        <textarea
        id="message"
        rows={3}
        value={message}
        onChange={(e) => setMessage(e.target.value)}
        className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-purple-500 focus:border-purple-500 text-gray-900 placeholder-gray-500"
        placeholder="Mensaje."
        required
        />
      </div>
      
      {error && (
        <div className="text-red-500 text-sm text-center">{error}</div>
      )}
      
      {notificationSent && (
        <div className="bg-green-50 border border-green-400 text-green-700 px-4 py-3 rounded-lg text-center">
        Notificación enviada exitosamente!
        </div>
      )}
      
      <div>
        <button
        type="submit"
        disabled={isSending}
        className="w-full flex justify-center py-3 px-4 border border-transparent rounded-lg shadow-md text-sm font-medium text-white bg-purple-500 hover:bg-purple-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-purple-400 disabled:opacity-50 disabled:cursor-not-allowed"
        >
        {isSending ? 'Enviando...' : 'Enviar Notificación'}
        </button>
      </div>
      </form>
      </div>
      </div>
    </div>
  );
}