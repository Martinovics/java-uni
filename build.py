import os
import sys
from typing_extensions import runtime



project_path = os.getcwd()
project_name = os.path.split(project_path)[-1]

commands = []

args = sys.argv[1:]
packages = os.listdir(f'./{project_name}')



if '-build' in args or '-b' in args or not args:

    command = ['javac']

    for package in packages:
        command.append(f'./{project_name}/{package}/*.java')

    commands.append(' '.join(command))



if '-jar' in args or '-j' in args:

    command = ['jar cf Jar.jar']

    for package in packages:
        command.append(f'./{project_name}/{package}/*.class')

    commands.append(' '.join(command))



if '-manifest' in args or '-m' in args:

    if not os.path.exists('./MANIFEST.mf') or '-newmanifest' or '-nm' in args:

        prog_path = input('Program path (package.name.Class): ')
        with open('./MANIFEST.mf', 'w', encoding='utf-8') as f:
            f.write(f'Main-Class: {prog_path}\n')


    command = ['jar cfm Jar.jar MANIFEST.mf']

    for package in packages:
        command.append(f'./{project_name}/{package}/*.class')

    commands.append(' '.join(command))



for command in commands:
    os.system(command)
    print()
