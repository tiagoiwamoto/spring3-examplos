#!/bin/bash
set -e

echo "🚀 Criando função Lambda no LocalStack..."

zip -j /tmp/lambda.zip /lambda/app.py  # <-- usa o caminho montado corretamente

# cria role fake só para satisfazer o parâmetro
awslocal iam create-role \
  --role-name exec-role \
  --assume-role-policy-document '{"Version":"2012-10-17","Statement":[{"Effect":"Allow","Principal":{"Service":"lambda.amazonaws.com"},"Action":"sts:AssumeRole"}]}'

# cria a função Lambda
awslocal lambda create-function \
  --function-name HelloTiago \
  --runtime python3.11 \
  --handler app.handler \
  --zip-file fileb:///tmp/lambda.zip \
  --role arn:aws:iam::000000000000:role/exec-role

echo "✅ Lambda HelloTiago criada com sucesso"
