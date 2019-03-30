package net.melonbun.melonbun.dagger;

import net.melonbun.melonbun.explore.ExploreFragment;
import net.melonbun.melonbun.post.PostRequestFragment;

import javax.inject.Singleton;
import dagger.Component;

/**
 * ApplicationComponent is the interface for telling dagger where to inject instances
 * and also provide injected instances by using component modules.
 */
@Singleton
@Component()
public interface ApplicationComponent {

    void inject(ExploreFragment exploreFragment);

    void inject(PostRequestFragment postRequestFragment);

}
