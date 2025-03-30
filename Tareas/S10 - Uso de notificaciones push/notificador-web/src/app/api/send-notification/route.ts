import { NextResponse } from 'next/server';
import { adminMessaging } from '@/firebase/adminApp';

export async function POST(request: Request) {
  try {
    const { title, body } = await request.json();
    
    if (!title || !body) {
      return NextResponse.json(
        { error: 'Título y mensaje son requeridos' },
        { status: 400 }
      );
    }
    
    const message = {
      notification: {
        title,
        body
      },
      topic: 'all',
      android: {
        priority: 'high'
      }
    };
    
    const response = await adminMessaging.send(message);
    
    return NextResponse.json(
      { success: true, messageId: response },
      { status: 200 }
    );
  } catch (error) {
    console.error('Error sending notification:', error);
    return NextResponse.json(
      { error: 'Error al enviar la notificación' },
      { status: 500 }
    );
  }
}