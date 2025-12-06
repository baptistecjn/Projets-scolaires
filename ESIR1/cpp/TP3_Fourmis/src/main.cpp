#include <iostream>
#include <SDL2/SDL.h>
#include <SDL2/SDL2_gfxPrimitives.h>
#include "Environment.h"
#include "Renderer.h"
#include <time.h>
#include "Timer.h"
#include "Food.h"
#include "Anthill.h"
#include "SillyAnt.h"
#include "Ant.h"
#include "AntWithRule.h"

static unsigned int windowWidth() { return 1024; }
static unsigned int windowHeight() { return 700; }

/// <summary>
/// called each time a key is pressed.
/// </summary>
/// <param name="key">The key.</param>
/// <param name="environment">The environment.</param>
void onKeyPressed(char key, Environment * environment)
{
    std::cout << "Key pressed: " << key << std::endl;

	// Créé une food si f est pressé
	if(key=='f'){
		int min = 200;
		int max = 2000;
		float qte = MathUtils::random(min, max);
		Vector2<float> position = environment->randomPosition();
		new Food(environment, position, qte);
	}

	// supprime une food si d est pressé
	if(key=='d'){
		auto liste_instances = environment->getAllInstancesOf<Food>();
		if(!liste_instances.empty()){
			liste_instances.front()->setStatus(Agent::destroy);
		}
	}

	// Créé une fourmilière et fait apparaitre 50 fourmis à une postion aléatoire si a est pressé
	if(key=='a'){
		Vector2<float> position = environment->randomPosition();
		Anthill* anthill = new Anthill(environment, position);
		for(int i = 0; i < 50; ++i){
			Vector2<float> antPosition = environment->randomPosition();
			//Vector2<float> direction(MathUtils::random(-1.0f, 1.0f), MathUtils::random(-1.0f, 1.0f));
			//direction.normalized();
			new AntWithRule(environment, antPosition, anthill);
		}
	}
	

}

/// <summary>
/// Called at each time step.
/// </summary>
void onSimulate(){
	Agent::simulate();
}

/// <summary>
/// The main program.
/// </summary>
/// <param name="argc">The number of arguments.</param>
/// <param name="argv">The arguments.</param>
/// <returns></returns>
int main(int /*argc*/, char ** /*argv*/)
{
	// 1 - Initialization of SDL
	if (SDL_Init(SDL_INIT_VIDEO | SDL_INIT_EVENTS/* | SDL_INIT_AUDIO*/) != 0) {
		SDL_Log("Unable to initialize SDL: %s", SDL_GetError());
		return 1;
	}
	// 2 - Initialization of the renderer
	Renderer::initialize(windowWidth(), windowHeight());

	// 3 - Creation of an environment
	Environment environment(windowWidth(), windowHeight());

	// 4 - We change the seed of the random number generator
	srand((unsigned int)time(NULL));

	// The main event loop...
	SDL_Event event;
	bool exit = false;


	while (!exit) 
	{
		// 1 - We handle events 
		while (SDL_PollEvent(&event))
		{
			if ((event.type == SDL_QUIT) || (event.type == SDL_KEYDOWN && event.key.keysym.sym == 'q'))
			{
				::std::cout << "Exit signal detected" << ::std::endl;
				exit = true;
				break;
			}
			if (event.type == SDL_KEYDOWN)
			{
				onKeyPressed((char)event.key.keysym.sym, &environment);
			}
		}
		// 2 - We update the simulation
		Timer::update(0.5);
		onSimulate();
		// 3 - We render the scene
		Renderer::getInstance()->flush();
	}

	std::cout << "Shutting down renderer..." << std::endl;
	Renderer::finalize();

	std::cout << "Shutting down SDL" << std::endl;
	SDL_Quit();

	Agent::finalize();

	return 0;
}
