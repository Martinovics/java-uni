import os
import sys
from typing import List, Dict




SOURCE_FOLDER = 'lab06'
NOTES: str = 'Run with:  java lab06.prog.Application'

DIRECTORIES: List[str] = []  # directories to create at start




def initialize() -> None:

    for directory in DIRECTORIES:
        if not os.path.exists(directory):
            os.mkdir(f'./{directory}')
            print(f'made directory: ./{directory}')




def run_build() -> None:

    commands = []

    args = sys.argv[1:]
    packages = os.listdir(f'./{SOURCE_FOLDER}')



    if '-build' in args or '-b' in args or not args:

        command = ['javac']

        for package in packages:
            command.append(f'./{SOURCE_FOLDER}/{package}/*.java')

        commands.append(' '.join(command))



    if '-jar' in args or '-j' in args:

        command = ['jar cf Jar.jar']

        for package in packages:
            command.append(f'./{SOURCE_FOLDER}/{package}/*.class')

        commands.append(' '.join(command))



    if '-manifest' in args or '-m' in args:

        if not os.path.exists('./MANIFEST.mf') or '-newmanifest' or '-nm' in args:

            prog_path = input('Program path (package.name.Class): ')
            with open('./MANIFEST.mf', 'w', encoding='utf-8') as f:
                f.write(f'Main-Class: {prog_path}\n')


        command = ['jar cfm Jar.jar MANIFEST.mf']

        for package in packages:
            command.append(f'./{SOURCE_FOLDER}/{package}/*.class')

        commands.append(' '.join(command))



    for command in commands:
        os.system(command)







def main():

    initialize()
    run_build()

    print(NOTES)

    return






if __name__ == '__main__':
    main()



