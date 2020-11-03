const path = require('path');
const nodeExternals = require('webpack-node-externals');

module.exports = {
    mode: 'development',
    target: 'web',
    entry: [ 'babel-polyfill', './src/index.js' ],
    devtool: 'source-map',
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                loader: "babel-loader"
            },
            {
                test: /\.html$/,
                loader: "html-loader"
            },
            {
                test: /\.(s*)css$/,
                use: [
                    {
                        loader: 'style-loader'
                    },
                    {
                        loader: 'css-loader'
                    },
                    {
                        loader: 'sass-loader'
                    }
                ]
            }
        ]
    },
    resolve: {
        extensions: ['.jsx', '.js', '.scss', '.css']
    },
    output: {
        filename: 'app.js',
        path: path.resolve(__dirname, '../../target/classes/static')
    },
    node: {
        fs: 'empty',
        net: 'empty',
        tls: 'empty'
    }
}
