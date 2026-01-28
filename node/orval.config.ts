import { defineConfig } from 'orval';

export default defineConfig({
  openapidemo: {
    input: './docs/api-docs.yaml',
    output: {
      httpClient: 'axios',
      mode: 'split',
      target: './src/api/api.ts',
      schemas: './src/api/types/',
      client: 'react-query',
      mock: true,
      override: {
        enumGenerationType: 'const',
        // mutator: {
        //   path: './src/api/mutator/customClient.ts',
        //   name: 'customClient',
        // },
        // formData: {
        //   path: './src/api/mutator/customFormData.ts',
        //   name: 'customFormData',
        // },
        query: {
          useQuery: true,
          useMutation: true,
          signal: false,
        },
        mock: {
          delay: 0,
        },
      },
    },
    hooks: {
      afterAllFilesWrite: 'prettier --write',
    },
  },
});
