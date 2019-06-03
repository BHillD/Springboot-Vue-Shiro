import {getRequest} from './axios'

export const initMenu = (router,store)=> {
    if(store.state.routes.length > 0){
        return;
    }

    getRequest("/info/menu").then(resp=> {
        if(resp && resp.status == 200){
            let fmtRoutes = formatRoutes(resp.data.obj);
            router.addRoutes(fmtRoutes);
            store.commit('initMenu', fmtRoutes);
        }
    })
}

export const formatRoutes = (routes)=> {
    let fmRoutes = [];
    routes.forEach(router=>{
        let{
            path,
            component,
            name,
            meta,
            iconCls,
            children
        } = router;
        if(children && children instanceof Array){
            children = formatRoutes(children);
        }
        let fmRouter = {
            path: path,
            component(resolve){
                if (component.startsWith("Home")) {
                    require(['../components/' + component + '.vue'], resolve)
                  } else if (component.startsWith("basicinfo")) {
                    require(['../components/employee/' + component + '.vue'], resolve)
                  } else if (component.startsWith("advanced")) {
                    require(['../components/employee/' + component + '.vue'], resolve)
                  } else if (component.startsWith("salary")) {
                    require(['../components/personnel/' + component + '.vue'], resolve)
                  } else if (component.startsWith("manage")) {
                    require(['../components/personnel/' + component + '.vue'], resolve)
                  }
            },
            name: name,
            iconCls: iconCls,
            meta: meta,
            children: children
        };
        fmRoutes.push(fmRouter);
    })
    return fmRoutes;
}