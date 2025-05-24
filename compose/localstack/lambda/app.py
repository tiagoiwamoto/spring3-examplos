import json

def handler(event, context):
    # Extrai algumas informações serializáveis do contexto
    context_info = {
        'function_name': context.function_name,
        'memory_limit_in_mb': context.memory_limit_in_mb,
        'aws_request_id': context.aws_request_id
    }

    # Gera uma string JSON com o conteúdo
    body = json.dumps({
        'context': context_info,
        'event': event,
        'message': 'Olá, Tiago!'
    })

    return {
        'statusCode': 200,
        'body': body,
        'headers': {
            'Content-Type': 'application/json'
        }
    }
